package it.fai.be.dto;

import fai.broker.models.Magazzino;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class MagazzinoDto {

    private Long oid;
    private String acronym;
    private String descr;
    private String comune;
    private String provincia;
    private String regione;
    private String cap;
    private String indirizzo;
    private String supplierServiceClassName;

    public static MagazzinoDto from (Magazzino m) {
        return builder()
                .oid(m.getOid())
                .acronym(m.getAcronym())
                .descr(m.getDescr())
                .comune(m.getComune())
                .provincia(m.getProvincia())
                .regione(m.getRegione())
                .cap(m.getCap())
                .indirizzo(m.getIndirizzo())
                .supplierServiceClassName(m.getSupplierServiceClassName())
                .build();
    }
}
