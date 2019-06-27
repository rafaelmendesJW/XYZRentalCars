package lab5.entidades;

public  enum  SituacaoCarro {
   disponivel {
      @Override
      public SituacaoCarro disponibilizar() {
         throw new IllegalStateException("Já está disponível");
      }

      @Override
      public SituacaoCarro alugar() {
         return alugado;
      }

      @Override
      public SituacaoCarro notificarForaPtOrigem() {
         throw  new IllegalStateException("Está disponivel!");
      }
   },alugado {
      @Override
      public SituacaoCarro disponibilizar() {
         return disponivel;
      }

      @Override
      public SituacaoCarro alugar() {
         throw new IllegalStateException("Já está Alugado");
      }

      @Override
      public SituacaoCarro notificarForaPtOrigem() {
         return fora_pt_origem;
      }
   },fora_pt_origem {
      @Override
      public SituacaoCarro disponibilizar() {
         return disponivel;
      }

      @Override
      public SituacaoCarro alugar() {
         throw new IllegalStateException("Está fora do ponto de Origem!");
      }

      @Override
      public SituacaoCarro notificarForaPtOrigem() {
         throw new IllegalStateException("Já está fora do Ponto de Origem!");
      }
   };

   public abstract SituacaoCarro disponibilizar();
   public abstract SituacaoCarro alugar();
   public abstract SituacaoCarro notificarForaPtOrigem();
}
