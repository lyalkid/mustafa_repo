package ru.itis.model;

import lombok.*;
import ru.itis.dto.CreateAnnouncementDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private String currency;
    private Long userId;
//    private Long imageId;

    public Announcement(CreateAnnouncementDto createAnnouncementDto) {
        this.id = createAnnouncementDto.getId();
        this.title = createAnnouncementDto.getTitle();
        this.description = createAnnouncementDto.getDescription();
        this.price = createAnnouncementDto.getPrice();
        this.currency = createAnnouncementDto.getCurrency();
        this.userId = createAnnouncementDto.getUserId();
//        this.imageId = createAnnouncementDto.getImageId();

    }

    public int currencyHelper(String currency) {
        if (currency.equals(Currency.RUB)) return 1;
        if (currency.equals(Currency.EUR)) return 2;
        return 3;

    }

    public String currencyHelper(int currency) {
        if (currency == 1) return Currency.RUB;
        if (currency == 2) return Currency.EUR;
        return Currency.USD;
    }

}
