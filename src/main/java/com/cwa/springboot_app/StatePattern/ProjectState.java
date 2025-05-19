package com.cwa.springboot_app.StatePattern;

import com.cwa.springboot_app.entity.Projet;

public interface ProjectState {
 public boolean changerEtat(Projet projet);
}
