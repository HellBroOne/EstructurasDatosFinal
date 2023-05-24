package utilerias.comunes;
/**
 * Clase enumerada para el tipo de renglon que se llevara.
 */
public enum TipoRenglon {
        SUPERIOR(1),
        INFERIOR(2);

        private int numeroRenglon;
        private TipoRenglon(int numeroOrden){
            this.numeroRenglon = numeroOrden;
        }

        public int getNumeroRenglon(){
            return numeroRenglon;
        }

        public void setNumeroRenglon(int numeroOrden) {
            this.numeroRenglon = numeroOrden;
        }

}
