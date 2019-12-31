package com.cms.webpage.website

import com.cms.webpage.website.dto.SetWebsiteDTO
import com.cms.webpage.website.dto.WebsiteDTO
import org.springframework.stereotype.Component

@Component
class WebsiteMapper {

    fun mapWebsiteToWebSiteDTO(website: Website): WebsiteDTO {
        return WebsiteDTO(website.pageTheme)
    }

    fun mapWebsiteDtoToWebsite(setWebsiteDTO: SetWebsiteDTO): Website {
        return Website(pageTheme = setWebsiteDTO.pageTheme)
    }
}