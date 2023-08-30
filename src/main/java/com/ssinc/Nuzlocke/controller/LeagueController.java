package com.ssinc.Nuzlocke.controller;

import com.ssinc.Nuzlocke.model.League;
import com.ssinc.Nuzlocke.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("League")
public class LeagueController {
    @Autowired
    LeagueService leagueService;

    @PostMapping
    public League createNewLeague(@RequestParam("displayName") String displayName,
                                  @RequestParam("ownerId") String ownerId,
                                  @RequestParam("secureId") String secureId) {

        return leagueService.createNewLeague(displayName, ownerId, secureId);
    }

    @GetMapping("/Leagues")
    public List<League> GetAllLeaguesByOwner(@RequestParam("ownerId") String ownerId) {
        return leagueService.getAllLeaguesByOwnerId(ownerId);
    }

    @GetMapping
    public League getLeaguesByOwnerId(@RequestParam("ownerId") String ownerId) {
        return leagueService.getLeaguesByOwnerId(ownerId);
    }
}
