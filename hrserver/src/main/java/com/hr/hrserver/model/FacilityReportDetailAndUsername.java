package com.hr.hrserver.model;

import com.hr.hrserver.pojo.FacilityReportDetail;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class FacilityReportDetailAndUsername {
    FacilityReportDetail facilityReportDetail;
    String username;
}
