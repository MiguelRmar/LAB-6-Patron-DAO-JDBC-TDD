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
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForo;
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
@ManagedBean (name="RegistroForosBean")
@SessionScoped
public class RegistroForosBean implements Serializable{
    
    private ServiciosForo sp=ServiciosForo.getInstance();
    private int idForo;
    private Set<Comentario> respuestas;
    
    public void setidForo(int num){
        this.idForo = num; 
    }
    
    public int getidForo(){
        return this.idForo;
    }
    
    public void RespuestasForo() throws ExcepcionServiciosForos {
        respuestas = sp.consultarEntradaForo(0).getRespuestas();
    }
     
    public String getUsuarioRespuesta(){
        return respuestas.iterator().next().getAutor().getNombre();
    }
    
    public String getRespuesta(){
        return respuestas.iterator().next().getContenido();
    }
    
    public Date getFechaRespuesta(){
        return respuestas.iterator().next().getFechayHora();
    }
}
