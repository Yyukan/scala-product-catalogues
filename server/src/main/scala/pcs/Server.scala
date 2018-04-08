package pcs

import pcs.server.ServerSupport

/**
  * Starts server with REST API
  */
object Server extends App with ServerSupport {
  start()
}
