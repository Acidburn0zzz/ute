package net.lmxm.ute.executers.tasks;

import net.lmxm.ute.beans.MavenArtifact;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.sources.MavenRepositorySource;
import net.lmxm.ute.beans.tasks.MavenRepositoryDownloadTask;
import net.lmxm.ute.event.JobStatusChangeEventBus;
import net.lmxm.ute.event.StatusChangeEventBus;
import net.lmxm.ute.utils.FileSystemTargetUtils;
import net.lmxm.ute.utils.PathUtils;
import net.lmxm.ute.utils.aether.AetherResolveUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.*;

public final class MavenRepositoryDownloadTaskExecuter extends AbstractTaskExecuter {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MavenRepositoryDownloadTaskExecuter.class);

    /**
     * The Maven Repository Download Task object to execute.
     */
    private final MavenRepositoryDownloadTask task;

    /**
     * Instantiates a new file system delete task executer.
     *
     * @param job  the job
     * @param task the task
     */
    public MavenRepositoryDownloadTaskExecuter(final Job job, final MavenRepositoryDownloadTask task) {
        super(job);

        this.task = checkNotNull(task, "Task may not be null");
    }

    /**
     * Resolves th provided MavenRepositorySource to a URL String.
     *
     * @param source the MavenRepositorySource to resolve to a URL
     * @return the resolved URL as a String
     */
    private String getFullUrl(final MavenRepositorySource source) {
        final String prefix = "execute() :";

        LOGGER.debug("{} entered", prefix);

        checkNotNull(source, "Source may not be null");

        final MavenRepositoryLocation location = checkNotNull(source.getLocation(), "Source location may not be null");

        final String url = StringUtils.trimToNull(location.getUrl());
        checkNotNull(url, "Source location url may not be blank");

        final String relativePath = source.getRelativePath();
        // Relative path may be null, StringUtils.join() will treat null values as empty strings

        final String fullUrl = PathUtils.buildFullPath(url, relativePath);

        LOGGER.debug("{} returning {}", prefix, fullUrl);

        return fullUrl;
    }

    /**
     * Execute the MavenRepositoryDownloadTask.
     */
    @Override
    public void execute() {
        final String prefix = "execute() :";

        LOGGER.debug("{} entered", prefix);

        try {
            final String mavenRepositoryUrl = getFullUrl(task.getSource());
            final File destinationDirectory = new File(FileSystemTargetUtils.getFullPath(task.getTarget()));
            final AetherResolveUtils aetherResolveUtils = new AetherResolveUtils(new JobStatusChangeEventBus(getJob()),
                    mavenRepositoryUrl);

            for (MavenArtifact mavenArtifact : task.getArtifacts()) {
                StatusChangeEventBus.important(MAVEN_REPOSITORY_DOWNLOAD_STARTED, getJob(), mavenArtifact.getCoordinates());

                aetherResolveUtils.resolveArtifact(mavenArtifact.getCoordinates(), destinationDirectory,
                        mavenArtifact.getTargetName());

                StatusChangeEventBus.important(MAVEN_REPOSITORY_DOWNLOAD_COMPLETED, getJob(), mavenArtifact.getCoordinates());
            }
        }
        catch (Exception e) {
            StatusChangeEventBus.error(MAVEN_REPOSITORY_DOWNLOAD_FAILED, getJob());
        }

        LOGGER.debug("{} returning", prefix);
    }
}
