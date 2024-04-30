package com.fofo.core.domain.member;

import com.fofo.core.domain.image.Image;

import java.util.List;

public record Form(MemberWithAddress memberWithAddress, List<Image> images) {

    public static Form of(final MemberWithAddress memberWithAddress, final List<Image> images) {
        return new Form(memberWithAddress, images);
    }

}
