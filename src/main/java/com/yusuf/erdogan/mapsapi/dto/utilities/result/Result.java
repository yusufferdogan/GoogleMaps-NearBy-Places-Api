package com.yusuf.erdogan.mapsapi.dto.utilities.result;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Result {

    @lombok.NonNull
    private boolean success;
    private String message;

}
