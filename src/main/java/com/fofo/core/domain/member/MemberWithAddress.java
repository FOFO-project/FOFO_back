package com.fofo.core.domain.member;

public record MemberWithAddress(
        Member member,
        Address address
) {

    public static MemberWithAddress of(final Member member, final Address address) {
        return new MemberWithAddress(member, address);
    }
}
