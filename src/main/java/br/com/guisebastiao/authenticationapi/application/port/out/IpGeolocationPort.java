package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.application.result.IpLocationResult;

import java.util.Optional;

public interface IpGeolocationPort {
    Optional<IpLocationResult> findLocation(String ipAddress);
}
