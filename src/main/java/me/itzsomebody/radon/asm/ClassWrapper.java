/*
 * Radon - An open-source Java obfuscator
 * Copyright (C) 2019 ItzSomebody
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package me.itzsomebody.radon.asm;

import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Wrapper for ClassNodes.
 *
 * @author ItzSomebody
 */
public class ClassWrapper {
    /**
     * Attached class node.
     */
    public ClassNode classNode;

    /**
     * Original name of ClassNode. Really useful when class got renamed.
     */
    public final String originalName;

    /**
     * Quick way of figuring out if this is represents library class or not.
     */
    public final boolean libraryNode;

    /**
     * Methods.
     */
    public final List<MethodWrapper> methods = new ArrayList<>();

    /**
     * Fields.
     */
    public final List<FieldWrapper> fields = new ArrayList<>();
    public final List<String> strConsts = new ArrayList<>();

    /**
     * Creates a ClassWrapper object.
     *
     * @param classNode   the attached {@link ClassNode}.
     * @param libraryNode is this a library class?
     */
    public ClassWrapper(ClassNode classNode, boolean libraryNode) {
        this.classNode = classNode;
        this.originalName = classNode.name;
        this.libraryNode = libraryNode;

        ClassWrapper instance = this;
        classNode.methods.forEach(methodNode -> methods.add(new MethodWrapper(methodNode, instance, methodNode.name,
                methodNode.desc)));
        if (classNode.fields != null)
            classNode.fields.forEach(fieldNode -> fields.add(new FieldWrapper(fieldNode, instance, fieldNode.name,
                    fieldNode.desc)));
    }

    public void addMethod(MethodNode methodNode) {
        methods.add(new MethodWrapper(methodNode, this, methodNode.name, methodNode.desc));
        classNode.methods.add(methodNode);
    }

    public void addStringConst(String s) {
        strConsts.add(s);
    }

    public MethodNode getMethod(String name, String desc) {
        return classNode.methods.stream().filter(methodNode -> name.equals(methodNode.name) && desc.equals(methodNode.desc))
                .findAny().orElse(null);
    }

    public MethodNode getOrCreateClinit() {
        MethodNode clinit = getMethod("<clinit>", "()V");

        if (clinit == null) {
            clinit = new MethodNode(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
            clinit.instructions.add(new InsnNode(Opcodes.RETURN));
            addMethod(clinit);
        }

        return clinit;
    }
}
