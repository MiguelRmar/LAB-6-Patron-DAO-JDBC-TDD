/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.managedbeans;


import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForo;
import java.util.List;
import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author hcadavid
 */

@ManagedBean
@SessionScoped
public class RegistroForosBean implements Serializable{
    
    private final ServiciosForo sp=ServiciosForo.getInstance();
    private int idForo =0;
    private String NombreForo;
    private String PreguntaForo;
    private Set<Comentario> respuestas;
    private Usuario usu = new Usuario("miguel.rojas-ma@mail.escuelaing.edu.co","Miguel Rojas");
    private Comentario com;
    private String comentario;
    private EntradaForo Foro;
    
    
    public void setForo(EntradaForo Foro){
        this.Foro = Foro;
        
    }
    
    public EntradaForo getForo(){
        return Foro;
    }
    
    public List<EntradaForo> getForos() throws ExcepcionServiciosForos{
        return sp.consultarEntradasForo();
    }
    
    public void newForo(String email, String nombre, int identificador, Usuario autor, String comentario, String titulo, Date fechayHora) throws ExcepcionServiciosForos{
        sp.registrarNuevaEntradaForo(new EntradaForo(identificador, sp.consultarUsuario(email), comentario, titulo, fechayHora));
    }
    
    public String getTituloForo() throws ExcepcionServiciosForos{
        return sp.consultarEntradaForo(idForo).getTitulo();
    }
    
    public String getPregunta() throws ExcepcionServiciosForos{
        return sp.consultarEntradaForo(idForo).getComentario();
    }
    
    public void setNombreForo(String nombre){
        this.NombreForo = nombre;
    }
    
    public String getNombreForo(){
        return this.NombreForo;
    }
    
    public void setPreguntaForo(String pregunta){
        this.PreguntaForo = pregunta;
    }
    
    public String getPreguntaForo(){
        return this.PreguntaForo;
    }
    
    public void setIdForo(int num){
        this.idForo = num; 
    }
    
    public int getIdForo(){
        return this.idForo;
    }
    
    public Set<Comentario> getRespuestasForo() throws ExcepcionServiciosForos {
        respuestas = sp.consultarEntradaForo(idForo).getRespuestas();
        return respuestas;
    }
     
    public String getRespuesta(){
        return this.comentario;
    }
     
    public void setRespuesta(String comentarioI){
        this.comentario = comentarioI;
    }
    
    public void agregarRespuesta() throws ExcepcionServiciosForos{
        com = new Comentario(usu, comentario,java.sql.Date.valueOf("2016-09-27"));
        sp.agregarRespuestaForo(idForo, com);
        respuestas = sp.consultarEntradaForo(idForo).getRespuestas();
        comentario = null;
    }
}
