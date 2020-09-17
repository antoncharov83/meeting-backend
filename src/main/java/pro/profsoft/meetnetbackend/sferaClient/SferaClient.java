package pro.profsoft.meetnetbackend.sferaClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.profsoft.meetnetbackend.exception.SferaGetCurrentProfileException;
import pro.profsoft.meetnetbackend.mapper.ProfileMapper;
import pro.profsoft.meetnetbackend.model.Profile;
import pro.profsoft.meetnetbackend.model.dto.sfera.AvatarDto;
import pro.profsoft.meetnetbackend.model.dto.sfera.ProfileDto;

import java.util.Collections;
import java.util.List;

@Service
public class SferaClient {
    private RestTemplate restTemplate;
    private ProfileMapper profileMapper;
    private final String baseUrl;

    public SferaClient(@Value("${sfera.baseurl}") String baseUrl, ProfileMapper profileMapper) {
        this.restTemplate = new RestTemplate();
        this.profileMapper = profileMapper;
        this.baseUrl = baseUrl;
    }

    public Profile getCurrentProfile(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ProfileDto> response = restTemplate
                .exchange(baseUrl + "/api/user/profiles/", HttpMethod.GET,
                        request, ProfileDto.class,1);

        if (response.getStatusCode() == HttpStatus.OK) {
            return profileMapper.profileDtoToProfile(response.getBody());
        } else {
            throw new SferaGetCurrentProfileException(token+" - Sfera not found profile");
        }
    }

    public List<AvatarDto> getImages(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<List<AvatarDto>> response = restTemplate
                .exchange(baseUrl + "/api/user/profile/"+id+"/avatars", HttpMethod.GET,
                        request, new ParameterizedTypeReference<List<AvatarDto>>() {}, 1);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new SferaGetCurrentProfileException(token+" - Sfera not found profile");
        }
    }
}
