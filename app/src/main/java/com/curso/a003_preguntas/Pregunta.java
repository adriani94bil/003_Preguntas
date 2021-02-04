package com.curso.a003_preguntas;

public class Pregunta {
    private String pregunta;
    private boolean respuesta;
    private String explicacion;

    public Pregunta(String pregunta, boolean respuesta, String explicacion) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.explicacion = explicacion;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }
}
