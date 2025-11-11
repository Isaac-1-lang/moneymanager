package in.projects.moneymanager.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import in.projects.moneymanager.dto.ProfileDto;
import in.projects.moneymanager.entity.ProfileEntity;
import in.projects.moneymanager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final EmailService emailService;

    public ProfileDto registerProfile(ProfileDto profileDto){
        ProfileEntity newProfile = toEntity(profileDto);
        newProfile.setActivationToken(UUID.randomUUID().toString());
        newProfile = profileRepository.save(newProfile);
        //send activation email
        String activationLink = "http://localhost:8080/api/v1.0/activate?token=" + newProfile.getActivationToken();
        String subject = "Activate your money account manager";
        String body = "Click on this link to activate your account: "+activationLink;
        emailService.sendEmail(newProfile.getEmail(), subject, body);

        return toDto(newProfile);
    }

    public ProfileEntity toEntity(ProfileDto profileDto){
        return ProfileEntity.builder()
        .fullName(profileDto.getFullName())
        .email(profileDto.getEmail())
        .password(profileDto.getPassword())
        .profileImageUrl(profileDto.getProfileImageUrl())
        .createdAt(profileDto.getCreatedAt())
        .updatedAt(profileDto.getUpdatedAt())
        .build();
    }

    public ProfileDto toDto(ProfileEntity profileEntity){
        return ProfileDto.builder()
        .id(profileEntity.getId())
        .fullName(profileEntity.getFullName())
        .email(profileEntity.getEmail())
        .profileImageUrl(profileEntity.getProfileImageUrl())
        .createdAt(profileEntity.getCreatedAt())
        .updatedAt(profileEntity.getUpdatedAt())
        .build();
    }

    public Optional<Object> activateProfile(String activationToken){
        return profileRepository.findByActivationToken(activationToken)
            .map((ProfileEntity profile) -> {
                profile.setIsActive(true);
                profileRepository.save(profile);
                return true;
            });
            
    }
}
