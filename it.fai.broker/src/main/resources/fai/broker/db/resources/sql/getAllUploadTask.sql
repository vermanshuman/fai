SELECT
   TASK.OID                 TASK_OID
  ,TASK.DESCR               TASK_DESCRIPTION
  ,TASK.CSV_FILE_NAME       TASK_CSV_FILE_NAME
  ,TASK.MAGAZZINO_ACRONYM   TASK_MAGAZZINO_ACRONYM
  ,TASK.OID_STATUS          OID_STATUS
  ,TASK.STATUS_TECH_DESCR   STATUS_TECH_DESCR
  ,TASK.STATUS_UPDATED_TS   STATUS_UPDATED_TS
  ,TASK.CREATION_TS         TASK_CREATION_TS
  ,CSV_STATUS.DESCR         STATUS_DESCR
FROM
    FAI_UPLOAD_TASK  TASK
   ,FAI_UPLOAD_CSV_STATUS CSV_STATUS
WHERE
      TASK.OID_STATUS = CSV_STATUS.OID
ORDER BY
    TASK.CREATION_TS
