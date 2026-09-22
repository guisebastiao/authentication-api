package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.application.result.IpLocationResult;

public interface IpGeolocationPort {
    IpLocationResult findLocation(String ipAddress);
}
