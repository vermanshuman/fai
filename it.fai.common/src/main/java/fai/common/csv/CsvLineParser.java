package fai.common.csv;

import java.util.ArrayList;
import java.util.List;

/**
 * La presenza dei demarcatori di stringa e dei relativi escape rende tutto abbastanza pi� complesso
 * da suggeriare di confinare tale complessit� in questa classe dedicata.<br/>
 * <br/>
 * In questa implementazione il delimitatore di stringa e l'escape per il delimitatore devono essere
 * lo stesso carattere.<br/>
 * <br/>
 * Sono, inoltre, supportate le stringhe multiriga, a patto che il campo multiriga sia l'ultimo
 * campo pi� a destra e che sia presente il delimitatore di stringa a demarcare l'inizio del
 * campo e la fine.<br/>
 * <br/>
 * In modalit� non-multiriga, il parser � tollerante alla presente di demarcatore preceduto da
 * escape anche in assenza di demarcatore di inizio riga (� tollerato [[{@code 1.7,ciao ""nuovo"" mondo,7.2}]]
 * anzich� [[{@code 1.7,"ciao ""nuovo"" mondo",7.2}]].<br/>
 * Sempre in modalit� non-multiriga il parser � tollerante a campi che recano il demarcatore di
 * inizio, ma non quello di fine, a patto che si tratti dell'ultimo campo pi� a destra (�
 * tollerato [[{@code 1.7,7.2,"un testo qualsiasi}]] anzich� [[{@code 1.7,7.2,"un testo qualsiasi"}]])
 *
 * @author
 */
class CsvLineParser {

    private String fieldSeparator;
    private String stringDelimiterChar;
    private boolean trimValueSpeaces;
    private boolean multiline;
    private int cursorIdx = 0;
    private String line = null;
    private String token = null;
    private StringBuffer tempToken = null;
    private boolean insideString = false;
    private List<String> tokens = null;


    public String getFieldSeparator() {
        return fieldSeparator;
    }


    public void setFieldSeparator(char fieldSeparator) {
        this.fieldSeparator = "" + fieldSeparator;
    }


    public String getStringDeimiterChar() {
        return stringDelimiterChar;
    }


    public void setStringDeimiterChar(char stringDeimiterChar) {
        stringDelimiterChar = "" + stringDeimiterChar;
    }


    public boolean isTrimValueSpeaces() {
        return trimValueSpeaces;
    }


    public void setTrimValueSpeaces(boolean trimValueSpeaces) {
        this.trimValueSpeaces = trimValueSpeaces;
    }


    public boolean isMultiline() {
        return multiline;
    }


    public void setMultiline(boolean multiline) {
        this.multiline = multiline;
    }


    private String getStringOf(String character, int length) {
        if (length == 0) return "";
        //
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < length; i++) {
            sb.append(character);
        }
        return sb.toString();

    }

    private void moveNextStep() {
        if (isEOL()) return;
        //
        //
        token = null;
        int idxFieldSeparator = line.indexOf(fieldSeparator, cursorIdx);
        int idxStringDeimiterChar = line.indexOf(stringDelimiterChar, cursorIdx);
        if (insideString == false) {
            //
            // --- insideString == false ---
            //
            if ((idxFieldSeparator >= 0 && idxFieldSeparator < idxStringDeimiterChar) || (idxFieldSeparator >= 0 && idxStringDeimiterChar == -1)) {
                token = line.substring(cursorIdx, idxFieldSeparator);
                cursorIdx = idxFieldSeparator + 1;
                return;
            } else if ((idxStringDeimiterChar >= 0 && idxStringDeimiterChar < idxFieldSeparator) || (idxStringDeimiterChar >= 0 && idxFieldSeparator == -1)) {
                StringBuffer delimCharSequence = new StringBuffer("");
                int currIdxBookmark = cursorIdx;
                while (cursorIdx < line.length() && stringDelimiterChar.equals(line.substring(cursorIdx, cursorIdx + 1))) {
                    delimCharSequence.append(line.substring(cursorIdx, cursorIdx + 1));
                    cursorIdx++;
                }
                String delimCharSeq = delimCharSequence.toString();
                if (delimCharSeq.length() % 2 == 0) { // � una sequenza interna alla stringa, es., [ etc etc ""hello world"" etc etc ]
                    if (multiline == true) {
                        throw new IllegalStateException("delimitatore con escape interno alla stringa, ma il contesto attuale non � una stringa (la modalit� multiline attiva non ammette la presenza di stringhe senza delimitatore di inizio e di fine); delimitatore con escalpe " + delimCharSeq + " (a partire dall'indice " + currIdxBookmark + "), riga: " + line);
                    } else {
                        //
                        tempToken = new StringBuffer("");
                        insideString = true;
                        String s1 = line.substring(currIdxBookmark, cursorIdx - delimCharSeq.length());
                        String s2 = getStringOf(stringDelimiterChar, delimCharSeq.length() / 2);
                        tempToken.append(s1);
                        tempToken.append(s2);
                    }
                } else { // � un delimitatore di inizio o fine stringa, es., [ etc etc, "stringa", """altra stringa""", """""altra stringa ancora""""" ] e, in questo contesto, di inizio
                    String s1 = getStringOf(stringDelimiterChar, (delimCharSeq.length() - 1) / 2);
                    tempToken = new StringBuffer(s1);
                    insideString = true;

                }
            } else if (idxFieldSeparator == -1 && idxStringDeimiterChar == -1) {
                token = line.substring(cursorIdx, line.length());
                cursorIdx = line.length();
            } else {
                throw new IllegalStateException("idxFieldSeparator = " + idxFieldSeparator + ", idxStringDeimiterChar = " + idxStringDeimiterChar);
            }
        } else {
            //
            // --- insideString == true ---
            //
            if (idxStringDeimiterChar >= 0) {
                StringBuffer delimCharSequence = new StringBuffer("");
                int currIdxBookmark = cursorIdx;
                cursorIdx = idxStringDeimiterChar;
                while (cursorIdx < line.length() && stringDelimiterChar.equals(line.substring(cursorIdx, cursorIdx + 1))) {
                    String s1 = line.substring(cursorIdx, cursorIdx + 1);
                    delimCharSequence.append(s1);
                    cursorIdx++;
                }
                String delimCharSeq = delimCharSequence.toString();
                if (delimCharSeq.length() > 0 && delimCharSeq.length() % 2 == 0) { // � una sequenza interna alla stringa, es., [ etc etc ""hello world"" etc etc ]
                    String s1 = line.substring(currIdxBookmark, cursorIdx - delimCharSeq.length());
                    String s2 = getStringOf(stringDelimiterChar, delimCharSeq.length() / 2);
                    tempToken.append(s1);
                    tempToken.append(s2);
                } else { // � un delimitatore di inizio o fine stringa, es., [ etc etc, "stringa", """altra stringa""", """""altra stringa ancora""""" ] e, in questo contesto, di fine
                    String s1 = line.substring(currIdxBookmark, cursorIdx - delimCharSeq.length());
                    tempToken.append(s1);
                    String s2 = getStringOf(stringDelimiterChar, (delimCharSeq.length() - 1) / 2);
                    tempToken.append(s2);
                    insideString = false;
                    token = tempToken.toString();
                    tempToken = null;
                    //
                    // rimozione di tutto quel che c'� tra il terminatore di stringa e la succesiva virgola [[a,1.2,""questa � una stringa" questo non dovrebbe esserci e va tolto,7.7]]
                    idxFieldSeparator = line.indexOf(fieldSeparator, cursorIdx);
                    if (idxFieldSeparator >= 0) {
                        cursorIdx = idxFieldSeparator + 1;
                    }
                }
            } else { // manca il delimitatore di fine stringa
                tempToken.append(line.substring(cursorIdx, line.length()));
                cursorIdx = line.length();
                if (multiline == false) {
                    insideString = false;
                } else {
                    tempToken.append("\r\n");
                }
                token = tempToken.toString();
            }
        }

    }

    private boolean hasToken() {
        return token != null && insideString == false;
    }

    public boolean hasTokenList() {
        return insideString == false;
    }

    private String popToken() {
        if (hasToken() == false) return null;
        //
        String s = token;
        token = null;
        return s;
    }

    private boolean isEOL() {
        if (cursorIdx > line.length()) throw new IllegalStateException("cursorIdx > line.length()");
        return cursorIdx == line.length();
    }

    public void parse(String line) {
        this.line = line;
        //
        if ("".equals(line) && multiline && insideString) {
            tempToken.append("\r\n");
            return;
        }
        //
        cursorIdx = 0;
        if (multiline == false || (multiline == true && insideString == false)) {
            tokens = new ArrayList<String>();
            insideString = false;
        }
        while (isEOL() == false) {
            moveNextStep();
            if (hasToken()) {
                String s = popToken();
                if (trimValueSpeaces && s != null) s = s.trim();
                tokens.add(s);
            }
        }
    }

    public List<String> getAllTokens() {
        if (hasTokenList() == false) return null;
        //
        return tokens;
    }


}
