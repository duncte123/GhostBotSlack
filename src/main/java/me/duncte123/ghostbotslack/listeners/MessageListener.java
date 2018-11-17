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

package me.duncte123.ghostbotslack.listeners;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://api.slack.com/docs/message-formatting
 */
public class MessageListener implements SlackMessagePostedListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
    private static final String prefix = "gb.";

    @Override
    public void onEvent(SlackMessagePosted event, SlackSession session) {
        SlackChannel channel = event.getChannel();
        SlackUser user = event.getSender();
        String content = event.getMessageContent();

        if (!content.toLowerCase().startsWith(prefix)) {
            return;
        }

        if (content.equalsIgnoreCase(prefix + "ping")) {
            long current = System.currentTimeMillis();
            String message = session.sendMessageOverWebSocket(channel, String.format("<@%s> pong", user.getId()))
                .getReply().getTimestamp();

            session.updateMessage(message, channel, String.format(
                "PONG!%nPing is: %sms",
                (System.currentTimeMillis() - current)
            ));
        }
    }
}
