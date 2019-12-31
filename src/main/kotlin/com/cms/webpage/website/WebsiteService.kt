package com.cms.webpage.website

import com.cms.webpage.user.Permission
import com.cms.webpage.user.PermissionChecker
import org.springframework.stereotype.Service

@Service
class WebsiteService(
    private val websiteRepository: WebsiteRepository,
    private val permissionChecker: PermissionChecker
) {
    fun getWebsite(): Website {
        return websiteRepository.findById(1L).orElseGet {
            Website()
        }
    }

    fun setWebsite(newWebsite: Website): Website {
        permissionChecker.hasPermissionOrThrow(Permission.TEMPLATE_CHANGE)
        return websiteRepository.save(newWebsite)
    }

}