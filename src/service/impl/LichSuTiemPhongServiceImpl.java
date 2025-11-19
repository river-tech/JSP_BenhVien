package service.impl;

import dao.LSTiemPhongDAO;
import dao.impl.LSTiemPhongDAOImpl;
import model.dto.LichSuTiemPhongView;
import service.LichSuTiemPhongService;

import java.util.List;

public class LichSuTiemPhongServiceImpl implements LichSuTiemPhongService {

    private final LSTiemPhongDAO lsTiemPhongDAO = new LSTiemPhongDAOImpl();

    @Override
    public List<LichSuTiemPhongView> getHistory() {
        return lsTiemPhongDAO.findHistoryView();
    }
}
