package log.refine

import Log

interface LogFilter {
    fun filter(log: Log): Boolean
}

