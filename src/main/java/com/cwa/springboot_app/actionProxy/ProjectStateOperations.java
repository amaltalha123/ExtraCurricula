package com.cwa.springboot_app.actionProxy;

import com.cwa.springboot_app.entity.Club;
import com.cwa.springboot_app.entity.Projet;

public interface ProjectStateOperations {
    public boolean changerEtat(Projet projet,Club club);
}
