package lab5.entidades;

public enum SituacaoReserva {
    ativa{
        @Override
        public SituacaoReserva ativar() {
            throw new IllegalStateException("Já está ativo!");
        }

        @Override
        public SituacaoReserva finalizar() {
            return finalizada;
        }

        @Override
        public SituacaoReserva notificarAtraso() {
            return atrasada;
        }
    },
    atrasada {
        @Override
        public SituacaoReserva ativar() {
            throw new IllegalStateException ("Atrasado!");
        }

        @Override
        public SituacaoReserva finalizar() {
            return finalizada;
        }

        @Override
        public SituacaoReserva notificarAtraso() {
            throw new IllegalStateException("Já foi notificado o atraso!");
        }
    }, finalizada {
        @Override
        public SituacaoReserva ativar() {
           throw new IllegalStateException("Já foi finalizada!");
        }

        @Override
        public SituacaoReserva finalizar() {
            throw new IllegalStateException("Já foi finalizada!");
        }

        @Override
        public SituacaoReserva notificarAtraso() {
            throw new IllegalStateException("Já foi finalizada!");
        }
    };

    public abstract  SituacaoReserva ativar();
    public abstract  SituacaoReserva finalizar();
    public abstract SituacaoReserva notificarAtraso();
}
