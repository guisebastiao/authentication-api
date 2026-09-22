package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.port.in.CreateRefreshUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.RefreshRepositoryPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SecureHasherPort;
import br.com.guisebastiao.authenticationapi.application.port.out.SecureRandomGeneratorPort;
import br.com.guisebastiao.authenticationapi.domain.model.Refresh;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

public class CreateRefreshService implements CreateRefreshUseCase {
    private static final int REFRESH_TOKEN_SIZE = 32;

    private final RefreshRepositoryPort refreshRepository;
    private final SecureRandomGeneratorPort secureRandomGenerator;
    private final SecureHasherPort secureHasher;

    public CreateRefreshService(
            RefreshRepositoryPort refreshRepository,
            SecureRandomGeneratorPort secureRandomGenerator,
            SecureHasherPort secureHasher
    ) {
        this.refreshRepository = refreshRepository;
        this.secureRandomGenerator = secureRandomGenerator;
        this.secureHasher = secureHasher;
    }

    @Override
    public CreateRefreshResult execute(Session session) {
        String refreshToken = secureRandomGenerator.generate(REFRESH_TOKEN_SIZE);

        Refresh refresh = createRefresh(session, refreshToken);

        return new CreateRefreshResult(refreshToken, refresh);
    }

    @Override
    public CreateRefreshResult execute(Session session, Refresh replacedBy) {
        String refreshToken = secureRandomGenerator.generate(REFRESH_TOKEN_SIZE);

        Refresh refreshEntity = createRefresh(session, refreshToken);
        refreshEntity.setReplacedBy(replacedBy);

        Refresh refresh = refreshRepository.save(refreshEntity);

        return new CreateRefreshResult(refreshToken, refresh);
    }

    private Refresh createRefresh(Session session, String token) {
        String refreshTokenHash = secureHasher.hash(token);

        Refresh refreshEntity = new Refresh();

        refreshEntity.setSession(session);
        refreshEntity.setRefreshTokenHash(refreshTokenHash);

        return refreshEntity;
    }
}
