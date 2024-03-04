package com.group.libraryapp.controller.company;

import com.group.libraryapp.domain.company.Team;
import com.group.libraryapp.dto.company.TeamRequest;
import com.group.libraryapp.dto.company.TeamResponse;
import com.group.libraryapp.service.company.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/teams")
@RestController
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<Team> saveTeam(@RequestBody TeamRequest request) {
        Team team = teamService.saveTeam(request);

        return ResponseEntity.ok(team);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        List<TeamResponse> teamsInfo = teamService.getAllTeamsInfo();

        return ResponseEntity.ok(teamsInfo);
    }
}
