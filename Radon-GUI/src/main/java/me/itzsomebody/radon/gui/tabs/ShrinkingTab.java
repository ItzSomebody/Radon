/*
 * Copyright (C) 2018 ItzSomebody
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

package me.itzsomebody.radon.gui.tabs;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import me.itzsomebody.radon.transformers.shrinkers.ShrinkerDelegator;
import me.itzsomebody.radon.transformers.shrinkers.ShrinkerSetup;

public class ShrinkingTab extends JPanel {
    private JCheckBox attributesCheckBox;
    private JCheckBox debugInfoCheckBox;
    private JCheckBox invisibleAnnotationsCheckBox;
    private JCheckBox visibleAnnotationsCheckBox;
    private JCheckBox unusedCodeCheckBox;
    private JCheckBox unusedMembersCheckBox;
    private JCheckBox shrinkerEnabledCheckBox;

    public ShrinkingTab() {
        GridBagLayout gbl_this = new GridBagLayout();
        gbl_this.columnWidths = new int[]{0, 0};
        gbl_this.rowHeights = new int[]{0, 0, 0};
        gbl_this.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_this.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        this.setBorder(new TitledBorder("Shrinker"));
        this.setLayout(gbl_this);

        JPanel shrinkerSetupPanel = new JPanel();
        GridBagConstraints gbc_shrinkerSetupPanel = new GridBagConstraints();
        gbc_shrinkerSetupPanel.fill = GridBagConstraints.BOTH;
        gbc_shrinkerSetupPanel.gridx = 0;
        gbc_shrinkerSetupPanel.gridy = 1;
        this.add(shrinkerSetupPanel, gbc_shrinkerSetupPanel);
        GridBagLayout gbl_shrinkerSetupPanel = new GridBagLayout();
        gbl_shrinkerSetupPanel.columnWidths = new int[]{0, 0};
        gbl_shrinkerSetupPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_shrinkerSetupPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_shrinkerSetupPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        shrinkerSetupPanel.setBorder(new TitledBorder("Setup"));
        shrinkerSetupPanel.setLayout(gbl_shrinkerSetupPanel);

        attributesCheckBox = new JCheckBox("Remove Attributes");
        GridBagConstraints gbc_attributesCheckBox = new GridBagConstraints();
        gbc_attributesCheckBox.anchor = GridBagConstraints.WEST;
        gbc_attributesCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_attributesCheckBox.gridx = 0;
        gbc_attributesCheckBox.gridy = 0;
        attributesCheckBox.setEnabled(false);
        shrinkerSetupPanel.add(attributesCheckBox, gbc_attributesCheckBox);

        debugInfoCheckBox = new JCheckBox("Remove Unneeded Debugging Information");
        GridBagConstraints gbc_debugInfoCheckBox = new GridBagConstraints();
        gbc_debugInfoCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_debugInfoCheckBox.gridx = 0;
        gbc_debugInfoCheckBox.gridy = 1;
        debugInfoCheckBox.setEnabled(false);
        debugInfoCheckBox.setToolTipText("Removes innerclasses, outerclass, outermethod, etc.");
        shrinkerSetupPanel.add(debugInfoCheckBox, gbc_debugInfoCheckBox);

        invisibleAnnotationsCheckBox = new JCheckBox("Remove Invisible Annotations");
        GridBagConstraints gbc_invisibleAnnotationsCheckBox = new GridBagConstraints();
        gbc_invisibleAnnotationsCheckBox.anchor = GridBagConstraints.WEST;
        gbc_invisibleAnnotationsCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_invisibleAnnotationsCheckBox.gridx = 0;
        gbc_invisibleAnnotationsCheckBox.gridy = 2;
        invisibleAnnotationsCheckBox.setEnabled(false);
        shrinkerSetupPanel.add(invisibleAnnotationsCheckBox, gbc_invisibleAnnotationsCheckBox);

        visibleAnnotationsCheckBox = new JCheckBox("Remove Visible Annotations");
        GridBagConstraints gbc_visibleAnnotationsCheckBox = new GridBagConstraints();
        gbc_visibleAnnotationsCheckBox.anchor = GridBagConstraints.WEST;
        gbc_visibleAnnotationsCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_visibleAnnotationsCheckBox.gridx = 0;
        gbc_visibleAnnotationsCheckBox.gridy = 3;
        visibleAnnotationsCheckBox.setEnabled(false);
        shrinkerSetupPanel.add(visibleAnnotationsCheckBox, gbc_visibleAnnotationsCheckBox);

        unusedCodeCheckBox = new JCheckBox("Remove Unused Code");
        GridBagConstraints gbc_unusedCodeCheckBox = new GridBagConstraints();
        gbc_unusedCodeCheckBox.anchor = GridBagConstraints.WEST;
        gbc_unusedCodeCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_unusedCodeCheckBox.gridx = 0;
        gbc_unusedCodeCheckBox.gridy = 4;
        unusedCodeCheckBox.setEnabled(false);
        shrinkerSetupPanel.add(unusedCodeCheckBox, gbc_unusedCodeCheckBox);

        unusedMembersCheckBox = new JCheckBox("Remove Unused Members");
        GridBagConstraints gbc_unusedMembersCheckBox = new GridBagConstraints();
        gbc_unusedMembersCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_unusedMembersCheckBox.anchor = GridBagConstraints.WEST;
        gbc_unusedMembersCheckBox.gridx = 0;
        gbc_unusedMembersCheckBox.gridy = 5;
        unusedMembersCheckBox.setEnabled(false);
        shrinkerSetupPanel.add(unusedMembersCheckBox, gbc_unusedMembersCheckBox);

        shrinkerEnabledCheckBox = new JCheckBox("Enabled");
        GridBagConstraints gbc_shrinkerEnabledCheckBox = new GridBagConstraints();
        gbc_shrinkerEnabledCheckBox.insets = new Insets(0, 0, 5, 0);
        gbc_shrinkerEnabledCheckBox.anchor = GridBagConstraints.WEST;
        gbc_shrinkerEnabledCheckBox.gridx = 0;
        gbc_shrinkerEnabledCheckBox.gridy = 0;
        shrinkerEnabledCheckBox.addActionListener((e) -> {
            boolean enable = shrinkerEnabledCheckBox.isSelected();
            attributesCheckBox.setEnabled(enable);
            debugInfoCheckBox.setEnabled(enable);
            invisibleAnnotationsCheckBox.setEnabled(enable);
            visibleAnnotationsCheckBox.setEnabled(enable);
            unusedCodeCheckBox.setEnabled(enable);
            unusedMembersCheckBox.setEnabled(enable);
        });
        this.add(shrinkerEnabledCheckBox, gbc_shrinkerEnabledCheckBox);
    }

    public ShrinkerDelegator getShrinker() {
        return (shrinkerEnabledCheckBox.isSelected()) ? new ShrinkerDelegator(new ShrinkerSetup(visibleAnnotationsCheckBox.isSelected(), invisibleAnnotationsCheckBox.isSelected(), attributesCheckBox.isSelected(),
            debugInfoCheckBox.isSelected(), unusedCodeCheckBox.isSelected(), unusedMembersCheckBox.isSelected())) : null;
    }
}
