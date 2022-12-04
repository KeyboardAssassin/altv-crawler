package com.altvstatistics.web.api;


import com.altvstatistics.web.dto.AltvOnlineStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "altvapi", url = "https://api.altstats.net/")
public interface AltvApiClient {
    @GetMapping(value = "/api/v1/server/{serverId}")
    AltvOnlineStatus getOnlineStatus(@PathVariable("serverId") Long serverId);
}
