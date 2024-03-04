package com.group.libraryapp.service.company;

import com.group.libraryapp.domain.company.Team;
import com.group.libraryapp.dto.company.TeamRequest;
import com.group.libraryapp.dto.company.TeamResponse;
import com.group.libraryapp.repository.company.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public Team saveTeam(TeamRequest request) {
        return teamRepository.save(new Team(request.getName()));
    }

    public List<TeamResponse> getAllTeamsInfo() {
        return teamRepository.findAllTeamInfo();
    }
}
