package bg.tu_varna.sit.api.inputoutput.scholarship;

import bg.tu_varna.sit.persistence.entity.scholarship.banking.BankName;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankingInfoDTO {

    private BankName bankName;
    private String bankAccount;
}
