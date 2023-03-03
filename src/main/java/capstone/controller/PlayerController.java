package capstone.controller;

import capstone.DTO.PlayerDTO;
import capstone.DTO.PlayerStatsDTO;
import capstone.DTO.StatsDTO;
import capstone.entity.Player;
import capstone.entity.Stats;
import capstone.service.PlayerService;
import capstone.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private StatsService statsService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/list")
    public String listPlayers(Model theModel) {
        List<Player> thePlayers = playerService.getAllPlayers();

        theModel.addAttribute("players", thePlayers);

        return "players/list-players";
    }

    @GetMapping("/home")
    public String home() {
        return "players/home";
    }

    @GetMapping("/bracket")
    public String bracket() {
        return "players/bracket";
    }

    @GetMapping("/scores")
    public String scores() {
        return "players/scores";
    }



//    @GetMapping("/showFormForAdd")
//    public String showFormForAdd(Model theModel) {
//        Player thePlayer = new Player();
//
//        theModel.addAttribute("player", thePlayer);
//
//
//        return "players/playerForm";
//    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("playerId") int theId,
                                    Model theModel){

        Player thePlayer = playerService.getPlayerById(theId);


        theModel.addAttribute("player", thePlayer);

        return "players/playerForm";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("playerId") int theId) {
        playerService.deletePlayer(theId);

        return "redirect:/players/list";
    }

//    @PostMapping("/save")
//    public String createPlayer(@ModelAttribute("playerForm")PlayerStatsDTO playerStatsDTO) {
//        Player player = new Player(playerStatsDTO.getName(), playerStatsDTO.getTeam());
//        player.setName(playerStatsDTO.getName());
//        player.setTeam(playerStatsDTO.getTeam());
//
//
//        Stats stats = new Stats();
//
//
//        stats.setGamesPlayed(playerStatsDTO.getGamesPlayed() == null ? 0 : playerStatsDTO.getGamesPlayed());
//        stats.setGoals(playerStatsDTO.getGoals() == null ? 0 : playerStatsDTO.getGoals());
//        stats.setAssists(playerStatsDTO.getAssists() == null ? 0 : playerStatsDTO.getAssists());
//        stats.setPoints(playerStatsDTO.getPoints() == null ? 0 : playerStatsDTO.getPoints());
//        stats.setPlusMinus(playerStatsDTO.getPlusMinus() == null ? 0 : playerStatsDTO.getPlusMinus());
//        stats.setShots(playerStatsDTO.getShots() == null ? 0 : playerStatsDTO.getShots());
//        stats.setWins(playerStatsDTO.getWins() == null ? 0 : playerStatsDTO.getWins());
//        stats.setLosses(playerStatsDTO.getLosses() == null ? 0 : playerStatsDTO.getLosses());
//        stats.setOTL(playerStatsDTO.getOTL() == null ? 0 : playerStatsDTO.getOTL());
//        stats.setShutouts(playerStatsDTO.getShutouts() == null ? 0 : playerStatsDTO.getShutouts());
//        stats.setSVPercent(playerStatsDTO.getSVPercent() == null ? 0.0 : playerStatsDTO.getSVPercent());
//        stats.setShutouts(playerStatsDTO.getShutouts() == null ? 0 : playerStatsDTO.getShutouts());
//        stats.setSaves(playerStatsDTO.getSaves() == null ? 0 : playerStatsDTO.getSaves());
//        stats.setShotsAgainst(playerStatsDTO.getShotsAgainst() == null ? 0 : playerStatsDTO.getShotsAgainst());
//        stats.setGoalsAgainst(playerStatsDTO.getGoalsAgainst() == null ? 0 : playerStatsDTO.getGoalsAgainst());
//
//        player.setStats(stats);
//        stats.setPlayer(player);
//
//        playerService.createPlayer(player);
//        return "redirect:/players";
//    }

    @PostMapping("/showFormForAdd")
    public ResponseEntity<?> savePlayer(@RequestBody PlayerStatsDTO
    playerStatsDTO, Model theModel) {

//        if (playerStatsDTO.getStats() != null && playerDTO.getStats().getGamesPlayed() == null) {
//            playerDTO.getStats().setGamesPlayed(0);
//        }

        Player player = new Player();
        player.setName(playerStatsDTO.getName());
        player.setTeam(playerStatsDTO.getTeam());



        Stats stats = new Stats();
        stats.setGamesPlayed(playerStatsDTO.getGamesPlayed() == null ? 0 : playerStatsDTO.getGamesPlayed());
        stats.setGoals(playerStatsDTO.getGoals() == null ? 0 : playerStatsDTO.getGoals());
        stats.setAssists(playerStatsDTO.getAssists() == null ? 0 : playerStatsDTO.getAssists());
        stats.setPoints(playerStatsDTO.getPoints() == null ? 0 : playerStatsDTO.getPoints());
        stats.setPlusMinus(playerStatsDTO.getPlusMinus() == null ? 0 : playerStatsDTO.getPlusMinus());
        stats.setShots(playerStatsDTO.getShots() == null ? 0 : playerStatsDTO.getShots());
        stats.setWins(playerStatsDTO.getWins() == null ? 0 : playerStatsDTO.getWins());
        stats.setLosses(playerStatsDTO.getLosses() == null ? 0 : playerStatsDTO.getLosses());
        stats.setOTL(playerStatsDTO.getOTL() == null ? 0 : playerStatsDTO.getOTL());
        stats.setShutouts(playerStatsDTO.getShutouts() == null ? 0 : playerStatsDTO.getShutouts());
        stats.setSVPercent(playerStatsDTO.getSVPercent() == null ? 0.0 : playerStatsDTO.getSVPercent());
        stats.setGAA(playerStatsDTO.getGAA() == null ? 0.0 : playerStatsDTO.getGAA());
        stats.setSaves(playerStatsDTO.getSaves() == null ? 0 : playerStatsDTO.getSaves());
        stats.setShotsAgainst(playerStatsDTO.getShotsAgainst() == null ? 0 : playerStatsDTO.getShotsAgainst());
        stats.setGoalsAgainst(playerStatsDTO.getGoalsAgainst() == null ? 0 : playerStatsDTO.getGoalsAgainst());

        System.out.println(player);
        System.out.println(stats);
        playerService.createPlayer(player);
        statsService.createStats(stats);
        theModel.addAttribute("player", player);
        theModel.addAttribute("stats", stats);
        return ResponseEntity.ok().build();

    }


}
