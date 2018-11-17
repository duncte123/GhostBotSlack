/*
 *     GhostBot, a Slack bot made for all your Danny Phantom needs
 *     Copyright (C) 2018  Duncan "duncte123" Sterken
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.duncte123.ghostbotslack;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import me.duncte123.ghostbotslack.listeners.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GhostBotSlack {

    private static final Logger logger = LoggerFactory.getLogger(GhostBotSlack.class);

    private GhostBotSlack() {
        String token = "";


        SlackSession session = SlackSessionFactory
            .getSlackSessionBuilder(token)
            .withAutoreconnectOnDisconnection(true)
            .build();

        MessageListener listener = new MessageListener();

        session.addMessagePostedListener(listener);

        try {
            session.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GhostBotSlack();
    }

}
