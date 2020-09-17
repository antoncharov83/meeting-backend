package pro.profsoft.meetnetbackend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pro.profsoft.meetnetbackend.model.ProfileReport;
import pro.profsoft.meetnetbackend.model.dto.profile.MeetNetProfileDto;
import pro.profsoft.meetnetbackend.model.dto.profile.MessageAsapDto;
import pro.profsoft.meetnetbackend.service.CompatibilityService;
import pro.profsoft.meetnetbackend.service.ProfileReportService;
import pro.profsoft.meetnetbackend.service.ProfileService;

import java.util.Collection;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private ProfileService profileService;
    private ProfileReportService profileReportService;
    private CompatibilityService compatibilityService;

    public ProfileController(ProfileService profileService, ProfileReportService profileReportService,
                             CompatibilityService compatibilityService) {
        this.profileService = profileService;
        this.profileReportService = profileReportService;
        this.compatibilityService = compatibilityService;
    }

    @ApiOperation(value = "Проверка первого входа в сферу",
            notes = "запрашивает данные из Сферы и сравнивает с бд Митнет. profile-token - токен сферы после авторизации")
    @GetMapping("isFirstTime")
    public Boolean isFirstTime(@RequestHeader(value = "profile-token") String token) {
        return profileService.isFirstTime(token);
    }

    @ApiOperation(value = "Данные профиля по токену",
            notes = "запрашивает данные из Сферы и сохраняет в бд Митнет. profile-token - токен сферы после авторизации." +
                    "В возвращаемой json accountId - id профиля")
    @GetMapping("sfera")
    public MeetNetProfileDto getProfile(@RequestHeader(value = "profile-token") String token) {
        return profileService.getCurrent(token);
    }

    @ApiOperation(value = "Данные профиля по id",
            notes = "Данные по полученному account-id из бд Митнет")
    @GetMapping("{id}")
    public MeetNetProfileDto getById(@PathVariable Long id) {
        return profileService.getById(id);
    }

    @ApiOperation(value = "Данные профилей для главного экрана",
            notes = "По id и номеру страницы возвращает до 4-х профилей без дизлайков за месяц")
    @GetMapping(params = { "page" })
    public Collection<MeetNetProfileDto> getPaginated(@RequestHeader(value = "account-id") Long id,
                                                      @RequestParam("page") int page) {
        return profileService.getPaginated(id, page);
    }

    @ApiOperation(value = "Следующий профиль для главного экрана",
            notes = "По id и смещению возвращает новый профиль без дизлайков за месяц, offset - счетчик полученных профилей")
    @GetMapping("get-next")
    public MeetNetProfileDto getNext(@RequestHeader(value = "account-id") Long id) {
        return profileService.getNext(id);
    }

    @ApiOperation(value = "Дизлайк",
            notes = "По id ставит дизлайк. account-id - отправитель")
    @GetMapping("dislike/{id}")
    public MeetNetProfileDto dislikeProfile(@RequestHeader(value = "account-id") Long userId,
                               @PathVariable("id") Long anotherUserId) {
        return profileService.dislikeProfile(userId, anotherUserId);
    }

    @ApiOperation(value = "Возможно послать сообщение?",
            notes = "Проверяет кол-во сообщений новым пользователям за текущие сутки и возвращает true если менее 5")
    @GetMapping("message-asap")
    public MessageAsapDto canSendMessageAsap(Long senderId, Long targetId) {
        return profileService.canSendMessageASAP(senderId, targetId);
    }

    @ApiOperation(value = "Жалоба на фото",
            notes = "По id сохраняет жалобы на фото. account-id - отправитель")
    @GetMapping(value = "report", params = { "typeReports" })
    public void saveReport(@RequestHeader(value = "account-id") Long userId, Long imgId,
                           @RequestParam("typeReports") ProfileReport.TypeReport[] typeReports) {
        profileReportService.save(userId, imgId, typeReports);
    }

    @ApiOperation(value = "Процент совместимости",
            notes = "Считает процент совместимости")
    @GetMapping(value = "compatibility", params = { "firstId", "secondId" })
    public Integer getCompatibility(@RequestParam("firstId") Long firstId,
                                    @RequestParam("secondId")Long secondId) {
        return compatibilityService.calculateCompability(firstId, secondId);
    }
}
