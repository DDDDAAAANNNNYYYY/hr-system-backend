package com.hr.hrserver.pojo;

import com.hr.hrserver.model.FacilityReportDetailAndUsername;
import com.hr.hrserver.pojo.FacilityReport;
import com.hr.hrserver.pojo.FacilityReportDetail;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class FacilityReportAndUsername {
    FacilityReport facilityReport;
    String username;
    List<FacilityReportDetailAndUsername> details;
}