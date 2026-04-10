package com.example.reservacode.service;

import static org.mockito.ArgumentMatchers.any;
//@ExtendWith(MockitoExtension.class)
//class ReservaServiceTest {
//
//    @Mock
//    private ReservaRepository reservaRepository;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private SalaRepository salaRepository;
//    @InjectMocks
//    private ReservaService reservaService;

//    @Test
//    @DisplayName("Deve criar uma reserva com sucesso quando os dados forem válidos")
//    void criarReserva() {
//
//        ReservaRequestDTO request = new ReservaRequestDTO();
//        request.setSala("Sala 01");
//        request.setUsuario("Matheus");
//        request.setHoraInicio(LocalDateTime.now().plusHours(1)); // Futuro
//        request.setHoraFinal(LocalDateTime.now().plusHours(2));
//
//        Sala sala = new Sala();
//        sala.setNome("Sala 01");
//        sala.setAtiva(false); // No seu código, se estiver ativa(true) ele lança erro, correto?
//
//        Usuario usuario = new Usuario();
//        usuario.setNome("Matheus");
//
//        when(salaRepository.findByNome("Sala 01")).thenReturn(Optional.of(sala));
//        when(userRepository.findByNome("Matheus")).thenReturn(Optional.of(usuario));
//        when(reservaRepository.existsSalaAndByHoraInicioAndHoraFinal(any(), any())).thenReturn(false);
//
//        // Simula o salvamento retornando a própria entidade com um ID
//        Reserva reservaSalva = new Reserva();
//        reservaSalva.setId(1L);
//        reservaSalva.setUsuario(usuario);
//        reservaSalva.setSala(sala);
//        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaSalva);
//
//        // --- ACT (Agir) ---
//        ReservaRespondDTO response = reservaService.criarReserva(request);
//
//        // --- ASSERT (Verificar) ---
//        assertNotNull(response);
//        assertEquals("Matheus", response.getUsuario());
//        verify(reservaRepository, times(1)).save(any()); // Garante que o save foi chamado
//    }
//}

