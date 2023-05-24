package utilerias.matematicas;

public enum TipoLogaritmo {
    NATURAL(1),
    BASE2(2),
    BASE10(3);

    private int base;
    private TipoLogaritmo(int base){this.base = base;}

    public int getBase() {
        return base;
    }
}
