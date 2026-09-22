package br.com.guisebastiao.authenticationapi.application.result;

public record IpLocationResult(
        String country,
        String countryCode,
        String state,
        String city,
        Double latitude,
        Double longitude,
        String timezone
) {
}
