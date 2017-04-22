package com.rajendarreddyj.reports.model;

import com.rajendarreddyj.model.project.Developer;

import fr.opensagres.xdocreport.document.images.IImageProvider;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class DeveloperWithImage extends Developer {
    private final IImageProvider photo;

    public DeveloperWithImage(final String name, final String lastName, final String mail, final IImageProvider photo) {
        super(name, lastName, mail);
        this.photo = photo;
    }

    public IImageProvider getPhoto() {
        return this.photo;
    }
}
