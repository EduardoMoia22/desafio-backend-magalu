package ry.edu.Magalu.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import ry.edu.Magalu.entities.Channel;
import ry.edu.Magalu.entities.Status;
import ry.edu.Magalu.repositories.ChannelRepository;
import ry.edu.Magalu.repositories.StatusRepository;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {
    private final ChannelRepository channelRepository;

    private final StatusRepository statusRepository;

    public DataLoader(ChannelRepository channelRepository, StatusRepository statusRepository) {
        this.channelRepository  = channelRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Channel.Values.values())
                .map(Channel.Values::toChannel)
                .forEach(this.channelRepository::save);

        Arrays.stream(Status.Values.values())
                .map(Status.Values::toStatus)
                .forEach(this.statusRepository::save);
    }
}
