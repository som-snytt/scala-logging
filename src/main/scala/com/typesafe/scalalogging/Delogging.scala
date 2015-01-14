package com.typesafe.scalalogging

import org.slf4j.LoggerFactory

/** Logging interpolators. Since the loggers disappear, "delogging."
 *  
 *  The logger itself is implicit. If logging is rare and the expense
 *  of look-up is tolerable, then the logger may be summoned at the
 *  logging call-site.
 *
 *  Alternatively, in the usual way, an implicit logger instance may
 *  be installed somewhere convenient.
 *
 *  Interpolators are provided that log a formatted message at the
 *  various logging levels.
 *
 *  {{{
 *    debug"Value is $x and ratio is ${ x/y }%1.2f"
 *  }}}
 *
 *  An interpolation can be followed by an argument list specifying a
 *  marker object and/or an exception to log.
 *
 *  {{{
 *    debug"Failed!"(marker, e)
 *    error"Failed!"(marker, e)
 *  }}}
 *  
 */
object implicits {
  /** An implicit logger, named for the enclosing class of the call site. */
  implicit def `logger named for enclosure`: Logger = Delogging.default

  implicit class `logging interpolators`(val sc: StringContext) extends AnyVal {
    def fatal [A >: Any](args: A*): String = macro Delogging.fatal[A]
    def crisis[A >: Any](args: A*): String = macro Delogging.crisis[A]
    def error [A >: Any](args: A*): String = macro Delogging.error[A]
    def warn  [A >: Any](args: A*): String = macro Delogging.warn[A]
    def info  [A >: Any](args: A*): String = macro Delogging.info[A]
    def debug [A >: Any](args: A*): String = macro Delogging.debug[A]
    def trace [A >: Any](args: A*): String = macro Delogging.trace[A]
  }
}
