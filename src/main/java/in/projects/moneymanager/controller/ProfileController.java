package in.projects.moneymanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.projects.moneymanager.dto.ProfileDto;
import in.projects.moneymanager.service.ProfileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    final ProfileService profileService;
    @PostMapping("/register")
    public ResponseEntity <ProfileDto> registerProfile(@RequestBody ProfileDto profileDto){
        ProfileDto registeredProfile = profileService.registerProfile(profileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredProfile);
    }
}
