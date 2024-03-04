package com.group.libraryapp.repository.company;

import com.group.libraryapp.domain.company.Team;
import com.group.libraryapp.dto.company.TeamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT new com.group.libraryapp.dto.company.TeamResponse(t.name, " +
            "(SELECT e.name FROM Employee e WHERE e.isManager = true AND e.team = t), " +
            "(SELECT COUNT(e) FROM Employee e WHERE e.team = t)) " +
            "FROM Team t")
    List<TeamResponse> findAllTeamInfo();

}
