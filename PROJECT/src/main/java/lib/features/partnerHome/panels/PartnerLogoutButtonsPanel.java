package lib.features.partnerHome.panels;

import javax.swing.*;

import lib.common.Audio;
import lib.common.Helper;
import lib.common.IconTextButton;
import lib.theme.AppColors;

public class PartnerLogoutButtonsPanel extends JPanel
{
    public PartnerLogoutButtonsPanel()
    {
        super();
        this.setBackground(AppColors.lightGray);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        IconTextButton musicButton = new IconTextButton(Audio.running ? "Music" : "Mute Music", Helper.ScaleDown("PROJECT/src/main/assets/images/music.png"));
        musicButton.addActionListener(e ->
        {
            if (Audio.running)
            {
                Audio.pauseAudio();
                musicButton.setText("Music");
            }
            else
            {
                Audio.pauseAudio();
                musicButton.setText("Mute Music");
            }

        });
        this.add(musicButton);
        IconTextButton logoutButton = new IconTextButton("Sign Out", Helper.ScaleDown("PROJECT/src/main/assets/images/signout.png"));
        logoutButton.addActionListener(e ->
        {
            System.exit(0);

        });
        this.add(logoutButton);
    }

}
