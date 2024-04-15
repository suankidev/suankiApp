package com.suanki.jdbcReader

import java.sql.{Connection, DriverManager, ResultSet, Statement}

object JdbcReader {

  private[this] val DB_URL = "jdbc:oracle:thin:@//localhost:1521/PDBORCL"
  private[this] val USER   = "suanki"
  private[this] val PASS   = "TestPass#123"
  private[this] val QUERY  = ""

  def getJdbcConnection: () => Connection = { () =>
    {
      val driver = new oracle.jdbc.driver.OracleDriver()
      DriverManager.registerDriver(driver)

      val connection: Connection = DriverManager.getConnection(
        "jdbc:oracle:thin:suanki/yourpassword@localhost:1521/PDBORCL"
      )
      connection
    }
  }

  def executeJdbcQuery(query: String): (ResultSet, Connection) = {

    val QUERY                  = query
    val connection: Connection = getJdbcConnection()
    val statement: Statement   = connection.createStatement()
    val resultSet: ResultSet   = statement.executeQuery(QUERY)

    (resultSet, connection)

  }

  def apply(q: String): (ResultSet, Connection) = {
    executeJdbcQuery(q)
  }

}
