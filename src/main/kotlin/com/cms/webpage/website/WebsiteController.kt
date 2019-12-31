package com.cms.webpage.website

import com.cms.webpage.website.dto.SetWebsiteDTO
import com.cms.webpage.website.dto.WebsiteDTO
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/website")
@RestController
class WebsiteController(
    private val websiteService: WebsiteService,
    private val websiteMapper: WebsiteMapper

) {

    @GetMapping
    fun getWebsiteConfig(): WebsiteDTO {
        return websiteMapper.mapWebsiteToWebSiteDTO(websiteService.getWebsite())
    }

    @PutMapping
    fun editWebsiteConfig(
        @RequestBody setWebsiteDTO: SetWebsiteDTO
    ): WebsiteDTO {
        return websiteMapper.mapWebsiteToWebSiteDTO(
            websiteService.setWebsite(
                websiteMapper.mapWebsiteDtoToWebsite(setWebsiteDTO)
            )
        )
    }

}