package com.cms.webpage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
class WebpageApplication

fun main(args: Array<String>) {
    runApplication<WebpageApplication>(*args)
}
