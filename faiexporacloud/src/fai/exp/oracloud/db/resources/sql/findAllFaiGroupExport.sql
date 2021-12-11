SELECT
     GRP.OID                        GRP_OID
    ,GRP.GROUP_IDX                  GRP_GROUP_IDX
    ,GRP.GROUP_NAME_EXPORT          GRP_GROUP_NAME_EXPORT
    ,TAB.OID                        TAB_OID
    ,TAB.OID_GROUPEXPORT            TAB_OID_GROUPEXPORT
    ,TAB.SQL_QUERY_IN               TAB_SQL_QUERY_IN
    ,TAB.SQL_QUERY_IN_FILE          TAB_SQL_QUERY_IN_FILE
    ,TAB.SMTWTFS_EXPORT_PATTERN     TAB_SMTWTFS_EXPORT_PATTERN
    ,TAB.TABLE_NAME_ORACLE          TAB_TABLE_NAME_ORACLE
    ,TAB.TABLE_IDX                  TAB_TABLE_IDX
    ,TAB.TABLE_NAME_EXPORT          TAB_TABLE_NAME_EXPORT
    ,TAB.SQL_DDL                    TAB_SQL_DDL
    ,TAB.SQL_QUERY_OUT              TAB_SQL_QUERY_OUT
    ,TAB.SQL_QUERY_OUT_FILE         TAB_SQL_QUERY_OUT_FILE
    ,COL.OID                        COL_OID
    ,COL.OID_TABLEEXPORT            COL_OID_TABLEEXPORT
    ,COL.COLUMN_IDX                 COL_COLUMN_IDX
    ,COL.COLUMN_NAME_ORACLE         COL_COLUMN_NAME_ORACLE
    ,COL.COLUMN_NAME_EXPORT         COL_COLUMN_NAME_EXPORT
    ,COL.REQUIRED                   COL_REQUIRED
    ,COL.SQL_TYPE                   COL_SQL_TYPE
    ,COL.SQL_TYPE_LEN               COL_SQL_TYPE_LEN
    ,COL.SQL_TYPE_SCALE             COL_SQL_TYPE_SCALE
    ,COL.DATE_FORMAT_PATTERN        COL_DATE_FORMAT_PATTERN
    ,COL.NUMB_FORMAT_PATTERN        COL_NUMB_FORMAT_PATTERN
    ,COL.NUMB_FORMAT_GRPCH          COL_NUMB_FORMAT_GRPCH
    ,COL.NUMB_FORMAT_DECCH          COL_NUMB_FORMAT_DECCH
FROM
     FAI_GROUP_EXPORT  GRP 
    ,FAI_TABLE_EXPORT  TAB
    ,FAI_COLUMN_EXPORT COL
WHERE
     COL.OID_TABLEEXPORT = TAB.OID
     AND
     TAB.OID_GROUPEXPORT = GRP.OID
ORDER BY     
     GRP.GROUP_IDX
    ,GRP.GROUP_NAME_EXPORT
    ,TAB.TABLE_IDX
    ,TAB.TABLE_NAME_EXPORT
    ,COL.COLUMN_IDX
