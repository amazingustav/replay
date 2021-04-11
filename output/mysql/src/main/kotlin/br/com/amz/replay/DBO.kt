package br.com.amz.replay

import java.time.ZonedDateTime

abstract class DBO {
    lateinit var createdAt: ZonedDateTime
    lateinit var modifiedAt: ZonedDateTime
}
