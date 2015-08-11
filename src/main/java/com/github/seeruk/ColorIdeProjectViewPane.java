/**
 * This file is part of the "intellij-idea-custom-tree-bg" project.
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the LICENSE is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.github.seeruk;

import com.intellij.ide.projectView.impl.ProjectViewPane;
import com.intellij.ide.projectView.impl.ProjectViewTree;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * ColorIdeProjectViewPane
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class ColorIdeProjectViewPane extends ProjectViewPane {
    public static final String COLOR_IDE_PROJECT_VIEW_PANE = "ColorIdeProjectViewPane";

    public ColorIdeProjectViewPane(Project project) {
        super(project);
    }

    @NotNull
    @Override
    public String getId() {
        return COLOR_IDE_PROJECT_VIEW_PANE;
    }

    @Override
    protected ProjectViewTree createTree(final DefaultTreeModel treeModel) {
        final ProjectViewTree tree = super.createTree(treeModel);

        return (ProjectViewTree) colorizeTree(tree);
    }

    public static JTree colorizeTree(final JTree tree) {
        tree.setForeground(new Color(0, 0, 0));
        tree.setBackground(new Color(229, 229, 229));

        return tree;
    }
}
