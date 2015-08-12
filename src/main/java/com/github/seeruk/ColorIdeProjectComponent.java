/**
 * This file is part of the "intellij-idea-custom-tree-bg" project.
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the LICENSE is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * <p/>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.github.seeruk;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.ide.projectView.impl.ProjectViewPane;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.extensions.ExtensionPoint;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.extensions.ExtensionsArea;
import com.intellij.openapi.project.Project;
import com.intellij.ui.FileColorManager;
import com.intellij.ui.tabs.FileColorManagerImpl;
import org.jetbrains.annotations.NotNull;

import static com.intellij.ui.tabs.FileColorManagerImpl.setEnabledForProjectView;

/**
 * ColorIdeProjectComponent
 *
 * @author Elliot Wright <elliot@elliotwright.co>
 */
public class ColorIdeProjectComponent implements ProjectComponent {
    private Project project;

    public ColorIdeProjectComponent(Project project) {
        this.project = project;
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    public String getComponentName() {
        return "ColorIdeProjectComponent";
    }

    @Override
    public void projectOpened() {
        replaceStandardProjectViewWithColored();
        replaceProjectViewColorShit();
    }

    private void replaceProjectViewColorShit() {
        FileColorManager colorManager = FileColorManager.getInstance(project);

        if (colorManager instanceof FileColorManagerImpl) {
            setEnabledForProjectView(false);
        }
    }

    private void replaceStandardProjectViewWithColored() {
        final ExtensionsArea area = Extensions.getArea(project);
        final ExtensionPoint<AbstractProjectViewPane> extensionPoint = area.getExtensionPoint(AbstractProjectViewPane.EP_NAME);

        for (AbstractProjectViewPane extension : extensionPoint.getExtensions()) {
            if ((extension instanceof ProjectViewPane) && !(extension instanceof ColorIdeProjectViewPane)) {
                extensionPoint.unregisterExtension(extension);
            }
        }

        removeStandardProjectViewPaneIfExists();
    }

    private void removeStandardProjectViewPaneIfExists() {
        final AbstractProjectViewPane projectViewPane = ProjectView.getInstance(project).getProjectViewPaneById(ProjectViewPane.ID);
        if (projectViewPane != null) {
            ProjectView.getInstance(project).removeProjectPane(projectViewPane);
        }
    }

    @Override
    public void projectClosed() {
    }
}
