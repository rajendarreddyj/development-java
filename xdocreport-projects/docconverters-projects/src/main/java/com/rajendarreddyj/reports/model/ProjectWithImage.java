package com.rajendarreddyj.reports.model;

import java.io.File;
import java.io.InputStream;

import com.rajendarreddyj.model.project.Project;

import fr.opensagres.xdocreport.template.annotations.FieldMetadata;
import fr.opensagres.xdocreport.template.annotations.ImageMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class ProjectWithImage extends Project {
    public ProjectWithImage(final String name) {
        super(name);
    }

    public InputStream getLogo() {
        return ProjectWithImage.class.getResourceAsStream("logo.png");
    }

    @FieldMetadata(images = { @ImageMetadata(name = "imageNotExistsAndRemoveImageTemplate", behaviour = NullImageBehaviour.RemoveImageTemplate),
            @ImageMetadata(name = "imageNotExistsAndKeepImageTemplate", behaviour = NullImageBehaviour.KeepImageTemplate) })
    public InputStream getNullLogo() {
        return null;
    }

    public File getLogoFile() {
        return new File("src/main/resources/logo.png");
    }

    @FieldMetadata(images = { @ImageMetadata(name = "fileImageNotExistsAndRemoveImageTemplate", behaviour = NullImageBehaviour.RemoveImageTemplate),
            @ImageMetadata(name = "fileImageNotExistsAndKeepImageTemplate", behaviour = NullImageBehaviour.KeepImageTemplate) })
    public File getNullLogoFile() {
        return null;
    }
}
