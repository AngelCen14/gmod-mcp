// global functions
function getPlayerByNick(nick)
    for _, ply in ipairs(player.GetAll()) do
        if string.find(string.lower(ply:Nick()), string.lower(nick)) then
            return ply
        end
    end
end