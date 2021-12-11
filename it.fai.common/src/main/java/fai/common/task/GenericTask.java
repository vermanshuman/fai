package fai.common.task;

import java.sql.Connection;
import java.util.Calendar;

public interface GenericTask {

  void setup(String acronym, Calendar nowReference, Connection conn) throws Exception;

  String doJob() throws Exception;

}